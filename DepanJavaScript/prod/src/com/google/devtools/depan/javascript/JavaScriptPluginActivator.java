/*
 * Copyright 2009 The Depan Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.depan.javascript;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class JavaScriptPluginActivator implements BundleActivator {

  /** Plug-in ID used to identify this plug-in. */
  public static final String PLUGIN_ID = "com.google.devtools.depan.filesystem";

  public JavaScriptPluginActivator() {
  }

  @Override
  public void start(BundleContext context) throws Exception {
  }

  @Override
  public void stop(BundleContext context) throws Exception {
  }
}