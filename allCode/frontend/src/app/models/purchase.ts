import { House } from './house';
import { User } from './user';

export interface Purchase {
    id: number;
    user: User;
    house: House;
}