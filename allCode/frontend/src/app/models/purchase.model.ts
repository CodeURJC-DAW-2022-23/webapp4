import { House } from './house.model';
import { User } from './user.model';

export interface Purchase {
    id: number;
    user: User;
    house: House;
}