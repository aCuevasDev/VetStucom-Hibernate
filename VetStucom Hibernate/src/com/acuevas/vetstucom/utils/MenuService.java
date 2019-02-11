package com.acuevas.vetstucom.utils;

import java.util.ArrayList;
import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.model.UserPerfil;
import com.acuevas.vetstucom.model.Usuarios;

/**
 * Auxilary class with all the options on the user's menu.
 * 
 * @author Alex
 *
 */
public abstract class MenuService {
	// Moved to utils since I'm doing some behaviour here.

	private enum MenuMessages {
		VIEW_RECORDS("View Records."), VIEW_USERS("View Users."), CREATE_RECORD("Create Record."),
		DELETE_RECORD("Delete Record."), EDIT_RECORD("Edit Record."), CREATE_USER("Create User."),
		EDIT_USER("Edit User."), DELETE_USER("Delete User."), LOGOUT("Log out.");

		private String message;

		private MenuMessages(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return this.message;
		}
	}

	private static List<MenuOption> auxOptions = new ArrayList<>();
	private static List<MenuOption> vetOptions = new ArrayList<>();
	private static List<MenuOption> adminOptions = new ArrayList<>();

	// @formatter:off
	private static MenuOption viewRecords = new MenuOption(MenuMessages.VIEW_RECORDS.toString(), 1, UserPerfil.AUXILIAR);
	private static MenuOption viewUsers = new MenuOption(MenuMessages.VIEW_USERS.toString(), 2, UserPerfil.VETERINARIO);
	private static MenuOption createRecord = new MenuOption(MenuMessages.CREATE_RECORD.toString(), 3, UserPerfil.VETERINARIO);
	private static MenuOption deleteRecord = new MenuOption(MenuMessages.DELETE_RECORD.toString(), 5, UserPerfil.VETERINARIO);
	private static MenuOption editRecord = new MenuOption(MenuMessages.EDIT_RECORD.toString(), 4, UserPerfil.VETERINARIO);
	private static MenuOption createUser = new MenuOption(MenuMessages.CREATE_USER.toString(), 6, UserPerfil.ADMIN);
	private static MenuOption editUser = new MenuOption(MenuMessages.EDIT_USER.toString(), 7, UserPerfil.ADMIN);
	private static MenuOption deleteUser = new MenuOption(MenuMessages.DELETE_USER.toString(), 8, UserPerfil.AUXILIAR);
	private static MenuOption logout = new MenuOption(MenuMessages.LOGOUT.toString(), 9, UserPerfil.AUXILIAR);
	// @formatter:on

	static {
		buildAuxOptions();
		buildVetOptions();
		buildAdminOptions();
		buildLogOut();
		order();
	}

	public static List<MenuOption> getMenuList(Usuarios user) throws ApplicationException {
		UserPerfil userPerfil = UserPerfil.getUserType(user);
		switch (userPerfil) {
		case ADMIN:
			return adminOptions;
		case AUXILIAR:
			return auxOptions;
		case VETERINARIO:
			return vetOptions;
		default:
			return null;
		}
	}

	private static void order() {
		auxOptions.sort(null);
		vetOptions.sort(null);
		adminOptions.sort(null);
	}

	private static void buildAuxOptions() {
		auxOptions.add(viewRecords);
	}

	private static void buildVetOptions() {
		vetOptions.add(viewRecords);
		vetOptions.add(createRecord);
		vetOptions.add(deleteRecord);
		vetOptions.add(editRecord);
	}

	private static void buildAdminOptions() {
		adminOptions.add(viewRecords);
		adminOptions.add(createRecord);
		adminOptions.add(deleteRecord);
		adminOptions.add(editRecord);
		adminOptions.add(createUser);
		adminOptions.add(viewUsers);
		adminOptions.add(deleteUser);
		adminOptions.add(editUser);
	}

	private static void buildLogOut() {
		auxOptions.add(logout);
		vetOptions.add(logout);
		adminOptions.add(logout);
	}
}