/**
 * Represents the interface for form dialog functionality.
 */
export interface FormDialogInterface {
    /**
     * Opens the form dialog window.
     *
     * @param paragraphContent - The content of the dialog paragraph.
     * @param dialogListItems - An array of strings representing dialog list items.
     * @param header - The header of the dialog.
     */
    openDialogWindow(paragraphContent: string, dialogListItems: string[], header: string): void;
}
