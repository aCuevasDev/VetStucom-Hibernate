package com.acuevas.vetstucom.utils;

import java.util.ArrayList;
import java.util.List;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.model.UserPerfil;
import com.acuevas.vetstucom.model.Usuarios;
import com.acuevas.vetstucom.utils.MenuOption.Action;

/**
 * Auxilary class with all the options on the user's menu.
 * 
 * @author Alex
 *
 */
public abstract class MenuService {
	// Moved to utils since I'm doing some behaviour here.

	private static List<MenuOption> auxOptions = new ArrayList<>();
	private static List<MenuOption> vetOptions = new ArrayList<>();
	private static List<MenuOption> adminOptions = new ArrayList<>();

	// @formatter:off
	private static MenuOption viewRecords = new MenuOption(Action.VIEW_RECORDS, 1, UserPerfil.AUXILIAR);
	private static MenuOption viewUsers = new MenuOption(Action.VIEW_USERS, 2, UserPerfil.VETERINARIO);
	private static MenuOption createRecord = new MenuOption(Action.CREATE_RECORD, 3, UserPerfil.VETERINARIO);
	private static MenuOption deleteRecord = new MenuOption(Action.DELETE_RECORD, 5, UserPerfil.VETERINARIO);
	private static MenuOption editRecord = new MenuOption(Action.EDIT_RECORD, 4, UserPerfil.VETERINARIO);
	private static MenuOption createUser = new MenuOption(Action.CREATE_USER, 6, UserPerfil.ADMIN);
	private static MenuOption editUser = new MenuOption(Action.EDIT_USER, 7, UserPerfil.ADMIN);
	private static MenuOption deleteUser = new MenuOption(Action.DELETE_USER, 8, UserPerfil.AUXILIAR);
	private static MenuOption logout = new MenuOption(Action.LOGOUT, 9, UserPerfil.AUXILIAR);
	private static MenuOption login = new MenuOption(Action.LOGIN, 0, UserPerfil.AUXILIAR);
	private static MenuOption exit = new MenuOption(Action.EXIT, 0, UserPerfil.AUXILIAR);
	// @formatter:on

	static {
		buildAuxOptions();
		buildVetOptions();
		buildAdminOptions();
		buildLogOut();
		order();
	}

	public static List<MenuOption> getLoginMenu() {
		List<MenuOption> options = new ArrayList<>();
		options.add(login);
		options.add(exit);
		return options;

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