package post.client.view;

import post.client.controller.POST;

/**
 * A CashierView is an object that can be connected to a POST, and will
 * typically issue transactions to it.
 * @author woeltjen
 */
public interface CashierView {
    /**
     * Connect this cashier's view to the specified POST. This will allow the
     * view to begin issuing transactions to the POST.
     * @param post
     */
    public void connectTo(POST post);
}
