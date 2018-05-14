pragma solidity 0.4.23;

contract Casino {
    address public owner;
    uint256 public minimumBet;
    uint256 public totalBet;
    uint256 public numberOfBets;
    uint256 public maxAmountOfBets = 100;
    address[] public players;

    // The address of the player and => the user info
    mapping(uint => address[]) public betInfo;

    function() public payable {}

    function Casino(uint256 _minimumBet) public {
        owner = msg.sender;
        if (_minimumBet != 0) minimumBet = _minimumBet;
    }

    function kill() public {
        if (msg.sender == owner) selfdestruct(owner);
    }

    function checkPlayerExists(address player) public constant returns (bool){
        for (uint256 i = 0; i < players.length; i++) {
            if (players[i] == player) return true;
        }
        return false;
    }
    // To bet for a number between 1 and 10 both inclusive
    function bet(uint256 numberSelected) public payable {
        require(!checkPlayerExists(msg.sender));
        require(numberSelected >= 1 && numberSelected <= 10);
        require(msg.value >= minimumBet);

        betInfo[numberSelected].push(msg.sender);
        numberOfBets++;
        players.push(msg.sender);
        totalBet += msg.value;
    }

    function generateNumberWinner() public {
        uint256 winnerNumber = block.number % 10 + 1;
        distributePrizes(winnerNumber);
    }

    function distributePrizes(uint256 winnerNumber) public {
        uint256 moneyPerWinner = totalBet / betInfo[winnerNumber].length;

        for (uint256 k = 0; k < betInfo[winnerNumber].length; k++) {
            betInfo[winnerNumber][k].transfer(moneyPerWinner);
        }

        for (uint256 i = 1; i < 11; i++) {
            betInfo[i].length = 0;
        }

        totalBet = 0;
    }
}