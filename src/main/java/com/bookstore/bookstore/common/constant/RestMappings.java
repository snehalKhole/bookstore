package com.bookstore.bookstore.common.constant;
/**
 * @author SnehalKhole
 * @version $Id: RestMappings.java $$
 */
public interface RestMappings {

    String BASE_ENDPOINT = "/book";
    String API_BASE = BASE_ENDPOINT + "/api";
    String VERSION_V1 = API_BASE + "/v1";
    String ID_PARAM = "id";
    String ID_PATH_VARIABLE = "{" + ID_PARAM + "}";


    public interface BookDetailsMappings {
        String BASE = VERSION_V1 + "/bookdetail";
        String GET_ALL_BOOKS = "/list";
        String ADD_BOOK = "/book";
        String UPDATE="update";
    }


}
