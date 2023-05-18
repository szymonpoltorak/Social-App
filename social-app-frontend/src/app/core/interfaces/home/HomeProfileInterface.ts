/**
 * Represents the interface for the Home Profile service.
 */
export interface HomeProfileInterface {
    /**
     * Opens a dialog with the specified title and URL.
     *
     * @param title The title of the dialog.
     * @param url The URL to open in the dialog.
     */
    openDialog(title: string, url: string): void;
}
