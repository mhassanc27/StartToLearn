package com.ets.thcs.easythcsearch.helper;

/**
 * Created by Manirul on 10/23/2016.
 */
public class URLs {


    public static final String ROOT_URL = "http://akm.org.in/SimplifiedCodingChat/v1/";
    //public static final String BASE_URL = "http://pandelhop.us-west-2.elasticbeanstalk.com/";
    //public static final String BASE_URL = "http://192.168.0.101:8080/TutorFinder/";
    //public static final String BASE_URL = "http://LowCost-env.m6erdccd34.us-west-2.elasticbeanstalk.com/";
    public static final String BASE_URL = "http://192.168.0.174:8080/searchender/";
    public static final String URL_REGISTER = ROOT_URL + "register";
    public static final String URL_STORE_TOKEN = ROOT_URL + "storegcmtoken/";
    public static final String URL_FETCH_MESSAGES = ROOT_URL + "messages";
    public static final String URL_FETCH_CHAT_ROOM = ROOT_URL + "chatroom";
    public static final String URL_SEND_MESSAGE = ROOT_URL + "send";

    public static final String URL_UPDATE_CHAT_ROOM = ROOT_URL + "updateChatRoom";


    //public static final String URL_REGISTRATION_TEACHER = BASE_URL+"TeacherRegistrationServletController";
    //public static final String URL_REGISTRATION_STUDENT = BASE_URL+"StudentRegistrationServletController";

    public static final String URL_REGISTRATION_TEACHER = BASE_URL+"TeacherController";
    public static final String URL_REGISTRATION_STUDENT = BASE_URL+"StudentController/createStudent";

    public static final String URL_CREATE_STUDENT = BASE_URL+"StudentController/createStudent";
    public static final String URL_READ_STUDENT = BASE_URL+"StudentController/readStudent";
    public static final String URL_UPDATE_STUDENT = BASE_URL+"StudentController/updateStudent";
    public static final String URL_DELETE_STUDENT = BASE_URL+"StudentController/deleteStudent";

    public static final String URL_READ_STUDENT_BY_USERNAME_AND_PASSWORD =
            BASE_URL+"StudentLoginController/getStudentByUserNameAndUserPassword";

    public static final String URL_CREATE_STUDENT_ADDRESS = BASE_URL+"StudentAddressController/createStudentAddress";
    public static final String URL_READ_STUDENT_ADDRESS_BY_STUDENTID = BASE_URL+"StudentAddressController/readStudentAddressByStudentId";
    public static final String URL_UPDATE_STUDENT_ADDRESS = BASE_URL+"StudentAddressController/updateStudentAddress";
    public static final String URL_DELETE_STUDENT_ADDRESS = BASE_URL+"StudentAddressController/deleteStudentAddress";



    public static final String URL_SIGNIN_TEACHER = BASE_URL+"TeacherSignInServletController";
    public static final String URL_SIGNIN_STUDENT = BASE_URL+"StudentSignInServletController";
    public static final String URL_SHOP_REGISTTRATION = BASE_URL+"ShopServletController";
    public static final String URL_PUJO_REGISTTRATION = BASE_URL+"PandalServletController";

    //Third Party URLs
    public static final String URL_POSTAL_PINCODE = "https://api.postalpincode.in/pincode/";


}
