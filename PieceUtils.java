public class PieceUtils {

  public static Piece charToPiece(char c) {
    switch (c) {
      case '^' : return Piece.EMITTER_NORTH;
      case '>' : return Piece.EMITTER_EAST;
      case 'v' : return Piece.EMITTER_SOUTH;
      case '<' : return Piece.EMITTER_WEST;
      case '|' : return Piece.LASER_VERTICAL;
      case '-' : return Piece.LASER_HORIZONTAL;
      case '+' : return Piece.LASER_CROSSED;
      case '/' : return Piece.MIRROR_SW_NE;
      case '\\' : return Piece.MIRROR_NW_SE;
      case '#' : return Piece.WALL;
      case 'o' : return Piece.TARGET;
      case ' ' : return Piece.EMPTY;
      case '@' : return Piece.SNOWMAN;
    }

    return null;
  }



  public static Piece[][] charsToPieces(char[] description,
                                        int width, int height) {
  Piece[][] pieces = new Piece[width][height];
  int index = 0;
  for (int j = height - 1; j>= 0; j--)
    for (int i = 0; i<width; i++) {
      pieces[i][j] = charToPiece(description[index]);
      index++;
    }

  return pieces;
  }

  public static boolean isEmitter(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
      case EMITTER_EAST:
      case EMITTER_SOUTH:
      case EMITTER_WEST:
        return true;
    return false;
  }

  public static Coordinate findEmitter(Piece[][] pieces) {
    int n = pieces.length;
    int m = pieces[0].length;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (isEmitter(pieces[i][j])) {
          Coordinate c = new Coordinate(i,j);
          return c;
        }
     return null;
  }

  public static Piece hideLaser(Piece p) {
    switch (p) {
      case LASER_VERTICAL:
      case LASER_HORIZONTAL:
      case LASER_CROSSED:
        return Piece.EMPTY;
    }
    return p;
  }

  public static Piece addLaser(Piece p, boolean isHorizontal) {
    switch (p) {
      case EMPTY: return returnEmpty(isHorizontal);
      case LASER_VERTICAL:
      case LASER_HORIZONTAL: return returnLaser(p, isHorizontal);
      default: return p;
    }
  }

  private static Piece returnEmpty(boolean isHorizontal) {
    if (isHorizontal)
      return Piece.LASER_HORIZONTAL;
    else
      return Piece.LASER_VERTICAL;
  }

  private static Piece returnLaser(Piece p, boolean isHorizontal) {
    if (isHorizontal)
        switch (p) {
        case LASER_VERTICAL: return Piece.LASER_CROSSED;
        case LASER_HORIZONTAL: return Piece.LASER_HORIZONTAL;
      }
    else
      switch (p) {
        case LASER_VERTICAL: return Piece.LASER_VERTICAL;
        case LASER_HORIZONTAL: return Piece.LASER_CROSSED;
      }
    return null;
  }

  public static Coordinate move(Piece p, Coordinate c, int xo, int yo) {
    switch (p) {
      case EMITTER_NORTH: return (new Coordinate (c.getX(), c.getY()+1));         case EMITTER_EAST: return (new Coordinate(c.getX()+1, c.getY()));
      case EMITTER_SOUTH: return (new Coordinate(c.getX(), c.getY()-1));
      case EMITTER_WEST: return (new Coordinate(c.getX()-1, c.getY()));
      case WALL:
      case SNOWMAN:
      case TARGET: return (new Coordinate(c.getX(), c.getY()));
      case MIRROR_SW_NE: return (new Coordinate(c.getX()+yo, c.getY()+xo));
      case MIRROR_NW_SE: return (new Coordinate(c.getX()-yo, c.getY()-xo));
      default:  return (new Coordinate(c.getX()+xo, c.getY()+yo));
    }
  }

  public static Piece rotate(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
        return Piece.EMITTER_EAST;
      case EMITTER_EAST:
        return Piece.EMITTER_SOUTH;
      case EMITTER_SOUTH:
        return Piece.EMITTER_WEST;
      case EMITTER_WEST:
        return Piece.EMITTER_NORTH;
      case MIRROR_SW_NE:
        return Piece.MIRROR_NW_SE;
      case MIRROR_NW_SE:
        return Piece.MIRROR_SW_NE;
    }
    return p;
  }
}
