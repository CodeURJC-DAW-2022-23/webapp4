package com.idealtrip.idealTrip.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.idealtrip.idealTrip.model.Destination;
import com.idealtrip.idealTrip.model.User;
import com.idealtrip.idealTrip.service.DestinationService;
import com.idealtrip.idealTrip.service.TourismService;
import com.idealtrip.idealTrip.service.UserService;

@Controller
public class ServiceController {
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private TourismService tourismService;

    @Autowired
    private UserService userService;

    User currentUser;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            userService.findByEmail(principal.getName()).ifPresent(us -> currentUser = us);
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("curretUser", currentUser);

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/services")
    public String showServices(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        model.addAttribute("services", destinationService.findAllPageable(PageRequest.of(page, size,
                Sort.by("id").ascending())));
        int currentPage = page + 1;
        model.addAttribute("currentPage", currentPage);
        return "services";
    }

    @GetMapping("/services/{id}")
    public String showDestination(Model model, @PathVariable Long id) {
        Optional<Destination> destination = destinationService.findById(id);
        if (destination.isPresent()) {
            model.addAttribute("service", destination);
            return "information";
        } else {
            return "services";
        }
    }

    @GetMapping("/{id}/imageDestination")
	public ResponseEntity<Resource> downloadImageDestination(@PathVariable long id) throws SQLException {
		return destinationService.downloadImageDestination(id);
	}

}
