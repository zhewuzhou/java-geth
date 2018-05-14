package geth.demo;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Casino extends Contract {
    private static final String BINARY = "6080604052606460045534801561001557600080fd5b506040516020806105aa833981016040525160008054600160a060020a03191633600160a060020a0316179055801561004e5760018190555b5061054c8061005e6000396000f3006080604052600436106100b95763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630a50e36181146100bb5780632ca8c6d3146100d05780634081d916146100f757806341c0e1b51461012c578063642915c8146101415780636ca3fc3b146101785780637365870b146101905780638da5cb5b1461019b578063c38a8afd146101b0578063e08a96cd146101c5578063f71d96cb146101da578063fe5e1853146101f2575b005b3480156100c757600080fd5b506100b9610207565b3480156100dc57600080fd5b506100e561021a565b60408051918252519081900360200190f35b34801561010357600080fd5b50610118600160a060020a0360043516610220565b604080519115158252519081900360200190f35b34801561013857600080fd5b506100b961027e565b34801561014d57600080fd5b5061015c6004356024356102a5565b60408051600160a060020a039092168252519081900360200190f35b34801561018457600080fd5b506100b96004356102dc565b6100b96004356103b8565b3480156101a757600080fd5b5061015c61048d565b3480156101bc57600080fd5b506100e561049c565b3480156101d157600080fd5b506100e56104a2565b3480156101e657600080fd5b5061015c6004356104a8565b3480156101fe57600080fd5b506100e56104d0565b6001600a430601610217816102dc565b50565b60035481565b6000805b6005548110156102735782600160a060020a031660058281548110151561024757fe5b600091825260209091200154600160a060020a0316141561026b5760019150610278565b600101610224565b600091505b50919050565b60005433600160a060020a03908116911614156102a357600054600160a060020a0316ff5b565b6006602052816000526040600020818154811015156102c057fe5b600091825260209091200154600160a060020a03169150829050565b600081815260066020526040812054600254829182918115156102fb57fe5b049250600091505b60008481526006602052604090205482101561037f57600084815260066020526040902080548390811061033357fe5b6000918252602082200154604051600160a060020a039091169185156108fc02918691818181858888f19350505050158015610373573d6000803e3d6000fd5b50600190910190610303565b5060015b600b8110156103ad5760008181526006602052604081206103a490826104d6565b50600101610383565b505060006002555050565b6103c133610220565b156103cb57600080fd5b600181101580156103dd5750600a8111155b15156103e857600080fd5b6001543410156103f757600080fd5b600090815260066020908152604082208054600181810183559184529183209091018054600160a060020a03331673ffffffffffffffffffffffffffffffffffffffff19918216811790925560038054840190556005805493840181559093527f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db090910180549092161790556002805434019055565b600054600160a060020a031681565b60015481565b60045481565b60058054829081106104b657fe5b600091825260209091200154600160a060020a0316905081565b60025481565b8154818355818111156104fa576000838152602090206104fa9181019083016104ff565b505050565b61051d91905b808211156105195760008155600101610505565b5090565b905600a165627a7a72305820cfed089007968ffc0a28c173ccd66da0578bbcdf14df963d934776a547daf99f0029";

    public static final String FUNC_GENERATENUMBERWINNER = "generateNumberWinner";

    public static final String FUNC_NUMBEROFBETS = "numberOfBets";

    public static final String FUNC_CHECKPLAYEREXISTS = "checkPlayerExists";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_BETINFO = "betInfo";

    public static final String FUNC_DISTRIBUTEPRIZES = "distributePrizes";

    public static final String FUNC_BET = "bet";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_MINIMUMBET = "minimumBet";

    public static final String FUNC_MAXAMOUNTOFBETS = "maxAmountOfBets";

    public static final String FUNC_PLAYERS = "players";

    public static final String FUNC_TOTALBET = "totalBet";

    protected Casino(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Casino(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> generateNumberWinner() {
        final Function function = new Function(
                FUNC_GENERATENUMBERWINNER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> numberOfBets() {
        final Function function = new Function(FUNC_NUMBEROFBETS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> checkPlayerExists(String player) {
        final Function function = new Function(FUNC_CHECKPLAYEREXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(player)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> betInfo(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> distributePrizes(BigInteger winnerNumber) {
        final Function function = new Function(
                FUNC_DISTRIBUTEPRIZES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(winnerNumber)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> bet(BigInteger numberSelected, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(numberSelected)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> minimumBet() {
        final Function function = new Function(FUNC_MINIMUMBET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> maxAmountOfBets() {
        final Function function = new Function(FUNC_MAXAMOUNTOFBETS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> players(BigInteger param0) {
        final Function function = new Function(FUNC_PLAYERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> totalBet() {
        final Function function = new Function(FUNC_TOTALBET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Casino> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _minimumBet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minimumBet)));
        return deployRemoteCall(Casino.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Casino> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _minimumBet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_minimumBet)));
        return deployRemoteCall(Casino.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Casino load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Casino(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Casino load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Casino(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
