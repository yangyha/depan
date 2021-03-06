/*
 * Copyright 2007 The Depan Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.devtools.depan.eclipse.utils;

/**
 * An interface to get a String from a given Object. Used to compare different
 * objects with their String representation.
 *
 * @author ycoppel@google.com (Yohann Coppel)
 *
 */
public interface ViewerObjectToString {

  /**
   * get a String for <code>object</code>
   *
   * @param object the object
   * @return a String representation for <code>object</code>.
   */
  public String getString(Object object);
}
