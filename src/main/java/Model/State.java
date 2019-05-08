package Model;

import java.io.PrintWriter;
import java.util.Scanner;

public interface State {
    int doAction(DataPacket dataPacket, InitializeAllPlay allPlay);
}
