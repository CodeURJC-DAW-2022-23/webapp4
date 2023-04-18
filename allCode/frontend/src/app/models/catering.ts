import { Destination } from "./destination";

export interface Catering {
    id: number;
    nameFood: string;
    contentFood: string;
    nameDestination: string;
    destination: Destination;
    foodImageFile: Blob;
    imageFood: string;
}