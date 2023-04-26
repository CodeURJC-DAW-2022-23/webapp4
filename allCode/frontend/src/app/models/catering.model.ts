import { Destination } from "./destination.model";

export interface Catering {
    id: number;
    nameFood: string;
    contentFood: string;
    nameDestination: string;
    destination: Destination;
    foodImageFile: Blob;
    imageFood: string;
}