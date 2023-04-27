import { Destination } from "./destination.model";

export interface Catering {
    content: Catering[];
    id: number;
    nameFood: string;
    contentFood: string;
    nameDestination: string;
    destination: Destination;
    foodImageFile: Blob;
    imageFood: string;
}