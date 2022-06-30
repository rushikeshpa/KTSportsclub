package com.cybage.pojo;

public class changePasswordDTO {
String oldPassword;
String newPassword;

public changePasswordDTO()
{
	
}

public String getOldPassword() {
	return oldPassword;
}

public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
}

public String getNewPassword() {
	return newPassword;
}

public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}

@Override
public String toString() {
	return "changePassword [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
}


}
