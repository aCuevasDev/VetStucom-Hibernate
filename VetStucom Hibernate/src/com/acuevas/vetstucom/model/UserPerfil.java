package com.acuevas.vetstucom.model;

import com.acuevas.vetstucom.exceptions.ApplicationException;
import com.acuevas.vetstucom.exceptions.ApplicationException.AppErrors;

/**
 * Stores the profiles allowed to {@link Usuarios#tipoUsuario}, used to check
 * which type the user is.
 * 
 * @author Alex
 *
 */
public enum UserPerfil {
	AUXILIAR(1), VETERINARIO(2), ADMIN(3);

	int value;

	private UserPerfil(int value) {
		this.value = value;
	}

	/**
	 * DONT USE, ONLY USED IN {@link Usuarios#toString()}
	 * 
	 * @param user
	 * @return
	 */
	public static UserPerfil getUserTypeNoThrows(Usuarios user) {
		switch (user.getTipoUsuario()) {
		case 1:
			return UserPerfil.AUXILIAR;
		case 2:
			return UserPerfil.VETERINARIO;
		case 3:
			return UserPerfil.ADMIN;
		default:
			return null;
		}
	}

	public static UserPerfil getUserType(Usuarios user) throws ApplicationException {
		switch (user.getTipoUsuario()) {
		case 1:
			return UserPerfil.AUXILIAR;
		case 2:
			return UserPerfil.VETERINARIO;
		case 3:
			return UserPerfil.ADMIN;

		default:
			throw new ApplicationException(AppErrors.TYPE_DOESNT_EXIST);
		}
	}

	public static boolean isAllowed(Usuarios user, UserPerfil userPerfil) throws ApplicationException {
		if (getUserType(user).value >= userPerfil.value)
			return true;
		return false;
	}
}