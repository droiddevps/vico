/*
 * Copyright (c) 2021. Patryk Goworowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.patrykgoworowski.liftchart_common.data_set.entry.collection.composed

import pl.patrykgoworowski.liftchart_common.data_set.entry.collection.EntryCollection
import pl.patrykgoworowski.liftchart_common.data_set.entry.collection.EntryModel

public operator fun <Model : EntryModel> EntryCollection<Model>.plus(
    other: EntryCollection<Model>
): ComposedEntryCollection<Model> =
    ComposedEntryCollection(listOf(this, other))

public operator fun <Model : EntryModel> ComposedEntryCollection<Model>.plus(
    other: EntryCollection<Model>
): ComposedEntryCollection<Model> =
    ComposedEntryCollection(entryCollections + other)

public operator fun <Model : EntryModel> ComposedEntryCollection<Model>.plus(
    other: ComposedEntryCollection<Model>
): ComposedEntryCollection<Model> =
    ComposedEntryCollection(entryCollections + other.entryCollections)