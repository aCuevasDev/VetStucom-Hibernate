package com.acuevas.vetstucom.views;

import java.util.ArrayList;
import java.util.List;

import com.acuevas.vetstucom.model.UserPerfil;

public abstract class Menu {

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

	private MenuOption viewRecords = new MenuOption(MenuMessages.VIEW_RECORDS.toString(), 1, UserPerfil.AUXILIAR);
	private MenuOption viewUsers = new MenuOption(MenuMessages.VIEW_USERS.toString(), 2, UserPerfil.VETERINARIO);
	private MenuOption createRecord = new MenuOption(MenuMessages.CREATE_RECORD.toString(), 3, UserPerfil.VETERINARIO);
	private MenuOption deleteRecord = new MenuOption(MenuMessages.DELETE_RECORD.toString(), 5, UserPerfil.VETERINARIO);
	private MenuOption editRecord = new MenuOption(MenuMessages.VIEW_RECORDS.toString(), 4, UserPerfil.VETERINARIO);
	private MenuOption createUser = new MenuOption(MenuMessages.CREATE_USER.toString(), 6, UserPerfil.ADMIN);
	private MenuOption editUser = new MenuOption(MenuMessages.EDIT_USER.toString(), 7, UserPerfil.ADMIN);
	private MenuOption deleteUser = new MenuOption(MenuMessages.DELETE_USER.toString(), 8, UserPerfil.AUXILIAR);
	private MenuOption logout = new MenuOption(MenuMessages.VIEW_RECORDS.toString(), 1, UserPerfil.AUXILIAR);

	private void buildAuxOptions() {

	}
}
