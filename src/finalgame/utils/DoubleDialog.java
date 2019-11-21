package finalgame.utils;

public class DoubleDialog {
	private String _player1;
	private String _player2;
	
	public DoubleDialog (String player1Dialog, String player2Dialog) {
		this.setPlayer1(player1Dialog);
		this.setPlayer2(player2Dialog);
	}

	public String getPlayer1() {
		return _player1;
	}

	private void setPlayer1(String _player1) {
		this._player1 = _player1;
	}

	public String getPlayer2() {
		return _player2;
	}

	private void setPlayer2(String _player2) {
		this._player2 = _player2;
	}
}
