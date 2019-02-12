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

	public enum Action {
		VIEW_RECORDS("View Records."), VIEW_USERS("View Users."), CREATE_RECORD("Create Record."),
		DELETE_RECORD("Delete Record."), EDIT_RECORD("Edit Record."), CREATE_USER("Create User."),
		EDIT_USER("Edit User."), DELETE_USER("Delete User."), LOGOUT("Log out.");

		private String message;

		private Action(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return this.message;
		}
	}

	private Action action;
	private int order;
	private UserPerfil profile;

	public MenuOption(Action action, int order, UserPerfil profile) {
		this.action = action;
		this.order = order;
		this.profile = profile;
	}

	@Override
	public int compareTo(MenuOption option) {
		return this.order - option.order;
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

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

}
