package yano_server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by aximcore on 2014.10.11..
 */

import yano_server.YanoProto;
import yano_server.YanoProto.Person;
import yano_server.YanoProto.Person.Chosen;

import yano_server.YanoProto.Datas;

import com.google.protobuf.*;

public class Main {

    private void getData(Datas input){
            for (Person person : input.getPersonList())
            {
                System.out.println("Sajat Azon: " + person.getMeId());

                if(person.hasId())
                    System.out.println("Kapcsolatom azon: " + person.getId());

                switch (person.getChosen()) {
                    case Android:
                        System.out.println("Android");
                        break;
                    case iOS:
                        System.out.println("iOS");
                        break;
                    case Windows:
                        System.out.println("Windows Phone");
                        break;
                    case Others:
                        System.out.println("Others");
                }

            }
    }


    public static void main(String[] args) throws IOException, Exception{

        Main main = new Main();
        int i = 0;


        try {
            Mysql mysql = new Mysql();
        } catch (SQLException ex){
            System.out.println(ex.toString());
            System.exit(-1);
        }


        ServerSocket listener = new ServerSocket(9090);

        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    /*BufferedReader input =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));


                    StringBuilder builder = new StringBuilder();
                    String aux = "";

                    while ((aux = input.readLine()) != null) {
                        builder.append(aux);
                    }

                    Temp.Data = builder.toString();
                    System.out.println(Temp.Data);*/

                    //YanoDatas.newBuilder().build();
                    //YanoDatas v = new YanoDatas.parseFrom(new FileInputStream("valami"));

                    //Datas datas = new Datas.parseFrom(socket.getInputStream());

                    //data.parseDelimitedFrom(socket.getInputStream());

                    Datas datas = Datas.parseFrom(socket.getInputStream());
                    main.getData(datas);

                    System.out.println(datas.toString());

                    System.out.println( i++ +" "+ socket.getInetAddress()); // csatlakozófél ip-je
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }


        //YanoData Temp = YanoData.newBuilder().setId("Azonosító").setChosen(Chosen.Valami1).build();
        //YanoData valami = YanoData.newBuilder().setId("").s


        //System.out.println(asd.toString());
    }
}
