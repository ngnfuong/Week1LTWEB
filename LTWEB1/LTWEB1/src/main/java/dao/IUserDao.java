package dao;

import models.UserModel;

public interface IUserDao {
	UserModel findByUserName(String username);

	void insert(UserModel user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	void updatePassword(String email, String password);

}
