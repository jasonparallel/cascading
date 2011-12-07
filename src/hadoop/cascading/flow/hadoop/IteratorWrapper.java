/*
 * Copyright (c) 2007-2011 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
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

package cascading.flow.hadoop;

import java.util.Iterator;

import cascading.flow.FlowProcess;

/**
 *
 */
class IteratorWrapper implements Iterator
  {
  private final FlowProcess flowProcess;

  Iterator iterator;

  public IteratorWrapper( FlowProcess flowProcess )
    {
    this.flowProcess = flowProcess;
    }

  public void reset( Iterator iterator )
    {
    this.iterator = iterator;
    }

  @Override
  public boolean hasNext()
    {
    return iterator.hasNext();
    }

  @Override
  public Object next()
    {
    flowProcess.increment( MapReduceCounters.Reduce_Tuples_Read, 1 );

    return iterator.next();
    }

  @Override
  public void remove()
    {
    iterator.remove();
    }
  }
