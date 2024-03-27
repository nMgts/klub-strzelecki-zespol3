package pl.klubstrzelecki.serwer_klub_strzelecki.service;

import pl.klubstrzelecki.serwer_klub_strzelecki.model.User;

public interface UserService {
    User findUserById(long userId) throws Exception;
}
