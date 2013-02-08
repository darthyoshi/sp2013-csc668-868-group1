package post.client.controller;


import post.model.Receipt;
import post.model.Transaction;
import post.server.controller.Store;



/**
 * Interface POST
 */
public interface POST extends Store {

    /**
     * @return Receipt
     * @param transaction
     */
    public Receipt recordTransaction(Transaction transaction);

    /**
     * @return       TransactionBuilder
     */
    public TransactionBuilder startTransaction(  );


}
