import { Destination } from "./destination.model";
import { User } from "./user.model";

export interface Review {
    id: number;
    titleReview: string;
    ratingReview: number;
    contentReview: string;
    user: User;
    destination: Destination;
}