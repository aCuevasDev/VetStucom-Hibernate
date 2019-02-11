package com.acuevas.vetstucom.utils;

import com.acuevas.vetstucom.model.UserPerfil;
import com.acuevas.vetstucom.model.Usuarios;

/**
 * This class stores an option of the User's menu, implemented
 * {@link Comparable} in order to sort them appropriately no matter the
 * {@link Usuarios#tipoUsuario}
 * 
 * @author Alex
 *
 */
public class MenuOption implements Comparable<MenuOption> {

	public enum Actions {
		VIEW_RECORDS, VIEW_USERS, CREATE_RECORD, EDIT_RECORD, DELETE_RECORD, CREATE_USER, EDIT_USER, DELETE_USER, LOGOUT
		
		private String message;

		private MenuMessages(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return this.message;
		}
	}

	// SAVE ACTIONS HERE? WITH AN ENUM AND THEN CONTROLLER MAKES THE CALLS?

	private String message;
	private int order;
	private UserPerfil profile;

	public MenuOption(String message, int order, UserPerfil profile) {
		this.message = message;
		this.order = order;
		this.profile = profile;
	}

	@Override
	public int compareTo(MenuOption option) {
		return this.order - option.order;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the profile
	 */
	public UserPerfil getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(UserPerfil profile) {
		this.profile = profile;
	}

}
