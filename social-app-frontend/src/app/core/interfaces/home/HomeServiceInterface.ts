import { Observable } from "rxjs";
import { FriendData } from "@core/interfaces/home/FriendData";
import { UserData } from "@core/interfaces/home/UserData";

/**
 * Represents the interface for home service functionality.
 */
export interface HomeServiceInterface {
    /**
     * Retrieves the list of friends.
     *
     * @returns An Observable emitting an array of FriendData.
     */
    getFriendList(): Observable<FriendData[]>;

    /**
     * Retrieves the user data.
     *
     * @returns An Observable emitting a UserData object.
     */
    getUserData(): Observable<UserData>;
}
