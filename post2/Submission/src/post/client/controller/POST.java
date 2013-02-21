package post.client.controller;

import post.store.Store;

/**
 * A Point of Sale Terminal. Acts as a Store, but additionally allows
 * transactions to be initiated.
 * @author woeltjen
 */
public interface POST extends Store {
    /**
     * Initiate a new transaction. The returned TransactionBuilder object
     * may be used by POST's users to create submittable Transaction objects;
     * this is favored over Views constructing their own transaction, as it
     * allows the POST to inject its own logic during the transaction cycle
     * (for instance, to validate UPCs)
     * @return       TransactionBuilder
     */
    public TransactionBuilder startTransaction(  );
}
