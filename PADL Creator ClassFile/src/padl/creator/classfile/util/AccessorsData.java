/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Ga�l Gu�h�neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Ga�l Gu�h�neuc and others, see in file; API and its implementation
 ******************************************************************************/
package padl.creator.classfile.util;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2004/08/01
 */
public interface AccessorsData {
	int getCardinality();
	boolean matches(
		final char[] targetName,
		final ExtendedFieldInfo fieldInfo);
}
