import { Purchase } from './purchase.model';
import { Review } from './review.model';

export interface User {
    id: number;
    email: string;
    name: string;
    lastName: string;
    encodedPassword: string;
    roles: string[];
    profileAvatarFile: Blob;
    profileAvatar: string;
    purchases: Purchase[];
    reviews: Review[];

}