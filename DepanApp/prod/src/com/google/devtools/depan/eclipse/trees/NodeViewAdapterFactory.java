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

package com.google.devtools.depan.eclipse.trees;

import com.google.common.collect.Lists;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.model.IWorkbenchAdapter;

import java.util.List;

/**
 * @author ycoppel@google.com (Yohann Coppel)
 *
 * @param <E> Type of data associated to each Node<Element>.
 */
public class NodeViewAdapterFactory<E> implements IAdapterFactory {

  // NodeViewAdapterFactory should be parameterized, but cannot make static
  // reference to the non-static type E
  private static NodeViewAdapterFactory<?> instance = null;

  @SuppressWarnings("rawtypes")
  private static class TypeAdapter {
    private final Class fromType;
    private final IWorkbenchAdapter adapter;

    public TypeAdapter(Class fromType, IWorkbenchAdapter adapter) {
      this.fromType = fromType;
      this.adapter = adapter;
    }

    public IWorkbenchAdapter getAdapter(Object adaptableObject) {
      if (adaptableObject.getClass().isAssignableFrom(fromType)) {
        return adapter;
      }
      return null;
    }

    public Class getFromType() {
      // TODO Auto-generated method stub
      return fromType;
    }
  }

  private static List<TypeAdapter> knownAdapters = buildKnownAdapters();
  static { buildKnownAdapters(); }

  /**
   * Build the list of know adapter types at static initialization time.
   * Registration with platform is deferred until an instance can be created.
   */
  private static <E> List<TypeAdapter> buildKnownAdapters() {
    List<TypeAdapter> result = Lists.newArrayList();
    result.add(new TypeAdapter(
        CollapseDataWrapper.class, new CollapseDataWrapperAdapter<E>()));
    result.add(new TypeAdapter(
        CollapseTreeRoot.class, new CollapseTreeRootAdapter<E>()));
    result.add(new TypeAdapter(
        HierarchyRoot.class, new HierarchyRootAdapter()));
    result.add(new TypeAdapter(
        NodeWrapper.class, new NodeWrapperAdapter<E>()));
    result.add(new TypeAdapter(
        NodeWrapperRoot.class, new NodeWrapperRootAdapter<E>()));
    result.add(new TypeAdapter(
        SolitaryRoot.class, new SolitaryRootAdapter()));
    result.add(new TypeAdapter(
        ViewerRoot.class, new ViewerRootAdapter()));
    return result;
  }

  // suppressWarning, because getAdapter have a Class as parameter, but
  // Class should be parameterized. To update if the IAdapterFactory is updated.
  @Override
  @SuppressWarnings("rawtypes")
  public Object getAdapter(
      Object adaptableObject,
      Class adapterType) {
    if (adapterType != IWorkbenchAdapter.class) {
      return null;
    }

    for (TypeAdapter asso : knownAdapters) {
      IWorkbenchAdapter result = asso.getAdapter(adaptableObject);
      if (null != result) {
        return result;
      }
    }
    return null;
  }

  @Override
  public Class<?>[] getAdapterList() {
    return new Class[] {IWorkbenchAdapter.class};
  }

  public static <E> void register() {
    if (null == instance) {
      instance = new NodeViewAdapterFactory<E>();
    }

    IAdapterManager manager = Platform.getAdapterManager();
    for (TypeAdapter asso : knownAdapters) {
      manager.registerAdapters(instance, asso.getFromType());
    }
  }
}
