package geth.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Transfer;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.math.BigInteger.valueOf;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;
import static org.web3j.utils.Convert.Unit.ETHER;
import static org.web3j.utils.Convert.fromWei;
import static org.web3j.utils.Convert.toWei;


public class App {

    public static final int INIT_ETHER_NUM = 10000;
    public static final String BET_NUM_ETHER = "1000";
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static String DEFAULT_PASSWORD = "test";

    public static void main(String[] args) throws Exception {
        Web3j client = Web3j.build(new HttpService());
        logVersion(client);

        EthAccounts ethAccounts = client.ethAccounts().send();
        String mainAccount = ethAccounts.getAccounts().get(0);

        ClientTransactionManager clientTransactionManager = new ClientTransactionManager(client, mainAccount);
        List<String> accounts = createAccounts(client, mainAccount);

        String contractAddress = deployContract(client, clientTransactionManager);

        int selected = bet(client, accounts, contractAddress);

        shareMoney(client, clientTransactionManager, contractAddress, selected);

        for (String account : accounts) {
            EthGetBalance balance = client.ethGetBalance(account, LATEST).send();
            logger.info("Account: {}, balance: {}", account, fromWei(balance.getBalance().toString(), ETHER));
        }

        EthGetBalance normal = client.ethGetBalance(accounts.get(0), LATEST).send();
        EthGetBalance winner = client.ethGetBalance(accounts.get(accounts.size() - 1), LATEST).send();
        assert normal.getBalance().compareTo(winner.getBalance()) < 0;
    }

    private static void shareMoney(Web3j client, ClientTransactionManager clientTransactionManager, String contractAddress, int selected) throws Exception {
        Casino casino = Casino.load(contractAddress, client, clientTransactionManager, valueOf(10L), valueOf(1000000L));
        TransactionReceipt result = casino.distributePrizes(valueOf(selected)).send();
        logger.info("Call result at {}", result.getBlockHash());
    }

    private static int bet(Web3j client, List<String> accounts, String contractAddress) throws Exception {
        int selected = 1;
        for (String account : accounts) {
            ClientTransactionManager playerClientTransactionManager = new ClientTransactionManager(client, account);
            Casino casino = Casino.load(contractAddress, client, playerClientTransactionManager, valueOf(10L), valueOf(1000000L));
            selected++;
            TransactionReceipt bet = casino.bet(valueOf(selected), toWei(BET_NUM_ETHER, ETHER).toBigInteger()).send();
            logger.info("Account: {}, bet: {}", account, selected);
        }
        return selected;
    }

    private static String deployContract(Web3j client, ClientTransactionManager clientTransactionManager) throws Exception {
        Casino casino = Casino.deploy(client, clientTransactionManager, valueOf(10L),
                valueOf(1000000L), valueOf(1000000L)).send();
        String contractAddress = casino.getContractAddress();
        logger.info("Deploy successfully. at {}", contractAddress);
        return contractAddress;
    }

    private static List<String> createAccounts(Web3j client, String mainAccount) throws Exception {
        ClientTransactionManager clientTransactionManager = new ClientTransactionManager(client, mainAccount);
        Transfer transfer = new Transfer(client, clientTransactionManager);
        List<String> accounts = newArrayList();
        Admin admin = Admin.build(new HttpService());
        for (int i = 0; i < 3; i++) {
            NewAccountIdentifier newAccountIdentifier = admin.personalNewAccount(DEFAULT_PASSWORD).send();
            logger.info("New account: {}", newAccountIdentifier.getAccountId());
            admin.personalUnlockAccount(newAccountIdentifier.getAccountId(), DEFAULT_PASSWORD, valueOf(300L)).send();
            TransactionReceipt transactionReceipt = transfer.sendFunds(newAccountIdentifier.getAccountId(), BigDecimal.valueOf(INIT_ETHER_NUM), ETHER).send();
            logger.info("Trans receipt: {}", transactionReceipt.getBlockHash());
            accounts.add(newAccountIdentifier.getAccountId());
        }
        return accounts;
    }

    private static void logVersion(Web3j client) throws java.io.IOException {
        Web3ClientVersion web3ClientVersion = client.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        logger.info("Version: {}", clientVersion);
    }
}
