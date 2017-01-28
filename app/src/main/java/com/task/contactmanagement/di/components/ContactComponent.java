/*
 * Copyright (c) 2016 Filippo Engidashet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.task.contactmanagement.di.components;

import com.task.contactmanagement.MainActivity;
import com.task.contactmanagement.di.module.ContactModule;
import com.task.contactmanagement.di.scope.PerActivity;


import dagger.Component;

/**
 * Created by Rajeesh adambil on 29/01/2017.
 */
@PerActivity
@Component(modules = ContactModule.class, dependencies = ApplicationComponent.class)
public interface ContactComponent {

    void inject(MainActivity activity);
}
