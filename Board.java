public class Board {

  private Piece[][] board;
  private Coordinate emitter;

  public Board(Piece[][] board) {
    this.board    = board;
    this.emitter  = PieceUtils.findEmitter(board);
  }

  public boolean laserEnds(Coordinate c) {
    int height = board.length;
    int width = board[0].length;
    int x = c.getX();
    int y = c.getY();
    if  ((x < 0) || (x >= height) ||
         (y < 0) || (y >= width))
      return true;
    else {
      Piece piece = board[x][y];
      switch (piece) {
        case EMPTY:
        case LASER_VERTICAL:
        case LASER_HORIZONTAL:
        case LASER_CROSSED:
        case MIRROR_SW_NE:
        case MIRROR_NW_SE: return false;
        default: return true;
      }
    }
  }

  public Result calculateResult(Coordinate c) {
    int height = board.length;
    int width = board[0].length;
    int x = c.getX();
    int y = c.getY();
    if  ((x < 0) || (x >= height) ||
         (y < 0) || (y >= width))
      return Result.MISS;
    else {
      Piece piece = board[x][y];
      switch (piece) {
        case TARGET: return Result.HIT_TARGET;
        case SNOWMAN: return Result.MELT_SNOWMAN;
        default : return Result.MISS;

      }
    }
  }


  public Result fireLaser() {
    Coordinate current = new Coordinate(emitter.getX(), emitter.getY());
    int xo = 0;
    int yo = 0;
    while (true) {
      int x = current.getX();
      int y = current.getY();
      Piece piece = board[x][y];

      PieceUtils.addLaser(piece, true);  // ??

      Coordinate next = PieceUtils.move(piece, current, xo, yo);
      xo = next.getX() - current.getX();
      yo = next.getY() - current.getY();
      current = next; //? cum e cu pointerii ?

      if (laserEnds(current))
        break;
      }

     return calculateResult(current);
  }


  public void rotatePiece(Coordinate c) {
    assert c.getX() >= 0 && c.getX() < board.length
        && c.getY() >= 0 && c.getY() < board[0].length;

    board[c.getX()][c.getY()]
      = PieceUtils.rotate(board[c.getX()][c.getY()]);
  }
 
