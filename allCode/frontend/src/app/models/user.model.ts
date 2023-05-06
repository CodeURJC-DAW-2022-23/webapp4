import { Purchase } from './purchase.model';
import { Review } from './review.model';

export interface User {
    id: number;
    email: string;
    name: string;
    lastName: string;
    encodedPassword: string;
    roles: string[];
    profileAvatar: string;
    profileAvatarFile: Blob;
    purchases: Purchase[];
    reviews: Review[];

}