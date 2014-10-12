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
    static Mysql mysql;
    String chosen,relation;

    private void getData(Datas input){
            for (Person person : input.getPersonList())
            {
                //System.out.println("Sajat Azon: " + person.getUsername());

                //if(person.hasMyFriend())
                //    System.out.println("Kapcsolatom azon: " + person.getMyFriend());

                switch (person.getChosen()) {
                    case Android:
                        chosen = "Android";
                        //System.out.println("Android");
                        break;
                    case iOS:
                        chosen = "iOS";
                        //System.out.println("iOS");
                        break;
                    case Windows:
                        chosen = "Windows Phone";
                        //System.out.println("Windows Phone");
                        break;
                    case Others:
                        chosen = "Others";
                        //System.out.println("Others");
                }

                switch (person.getRelation()){
                    case Relationship:
                        relation = "Relationship";
                        //System.out.println("Relationship");
                        break;
                    case Father:
                        relation = "Father";
                        //System.out.println("Father");
                        break;
                    case Mother:
                        relation = "Mother";
                        //System.out.println("Mother");
                        break;
                    case Girlfriend:
                        relation = "Girlfriend";
                        //System.out.println("Girlfriend");
                        break;
                    case Boyfriend:
                        relation = "Boyfriend";
                        //System.out.println("Boyfriend");
                        break;
                    case Neighbor:
                        relation = "Neighbor";
                        //System.out.println("Neighbor");
                        break;
                    case Testver:
                        relation = "Testvér";
                        //System.out.println("Testvér");
                }

                mysql.Exec("INSERT INTO users (username,chosen) values ('"+person.getUsername()+"','"+chosen+"');");
                mysql.Exec("INSERT INTO relation (username,friend,relationship) values ('"+person.getUsername()+"'," +
                        "'"+person.getMyFriend()+"','"+relation+"');");

            }
    }


    public static void main(String[] args) throws IOException, Exception{

        Main main = new Main();
        mysql = new Mysql();
        int i = 0;




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

                    //System.out.println(datas.toString());

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
