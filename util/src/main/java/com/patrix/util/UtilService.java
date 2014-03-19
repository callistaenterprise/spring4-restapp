package com.patrix.util;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface UtilService {

	/**
	 * Map state from a source object to a target object. <p>
	 * 
	 * Use naming conventions to copy state, i.e. a member with the same name and public getter/setter will be mapped.
	 * 
	 * @param from the source object.
	 * @param cls the target type.
	 * @return the target object with mapped state.
	 */
	<T> T map(Object from, Class<T> cls);

	/**
	 * Return a mapped list.
	 * 
	 * @param from the source list to map from.
	 * @param cls the target type.
	 * 
	 * @return the mapped list of target objects.
	 */
	<T> List<T> map(List<?> from, Class<T> cls);


    /**
     * Returns current user.
     *
     * @return the user, which can be empty if authorization not has been carried out.
     */
    UserDetails getCurrentUser();
}
