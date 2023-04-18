import { Destination } from "./destination";
import { User } from "./user";

export interface Review {
    id: number;
    titleReview: string;
    ratingReview: number;
    contentReview: string;
    user: User;
    destination: Destination;
}