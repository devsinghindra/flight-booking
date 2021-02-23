package com.devsinghindra.flight;

import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;


public class ThriftTest {
    public static void main(String[] args) throws TTransportException {
//        createUser();
        getUser();
    }

    private static void createUser() throws TTransportException {
        THttpClient tHttpClient = new THttpClient("http://localhost:8082/user");
        TProtocol tProtocol = new TJSONProtocol(tHttpClient);
        TUserService.Client client=new TUserService.Client(tProtocol);
        TUserDetails tUserDetails=null;
        try {
            TUserCreateRequest tUserCreateRequest = new TUserCreateRequest("1234","Devendra",TUserType.ADMIN);
            client.signUp(tUserCreateRequest);
            System.out.println(tUserCreateRequest);
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (InternalServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getUser() throws TTransportException {
        THttpClient tHttpClient = new THttpClient("http://localhost:8082/user");
        TProtocol tProtocol = new TJSONProtocol(tHttpClient);
        TUserService.Client client=new TUserService.Client(tProtocol);
        TUserDetails tUserDetails=null;
        try {
            TUserCreateRequest tUserCreateRequest = new TUserCreateRequest("1234","Devendra",TUserType.ADMIN);
            tUserDetails = client.login(tUserCreateRequest);
            System.out.println(tUserDetails);
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (InternalServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
