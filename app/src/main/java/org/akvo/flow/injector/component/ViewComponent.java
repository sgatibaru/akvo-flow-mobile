/*
 * Copyright (C) 2016-2017 Stichting Akvo (Akvo Foundation)
 *
 * This file is part of Akvo Flow.
 *
 * Akvo Flow is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Akvo Flow is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Akvo Flow.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.akvo.flow.injector.component;

import org.akvo.flow.activity.RecordActivity;
import org.akvo.flow.activity.SettingsActivity;
import org.akvo.flow.injector.PerActivity;
import org.akvo.flow.injector.module.ViewModule;
import org.akvo.flow.presentation.AboutActivity;
import org.akvo.flow.presentation.legal.LegalNoticesActivity;
import org.akvo.flow.presentation.signature.SignatureActivity;
import org.akvo.flow.ui.fragment.DrawerFragment;
import org.akvo.flow.ui.fragment.ResponseListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ViewModule.class)
public interface ViewComponent {

    void inject(RecordActivity recordActivity);

    void inject(ResponseListFragment responseListFragment);

    void inject(SettingsActivity settingsActivity);

    void inject(AboutActivity aboutActivity);

    void inject(LegalNoticesActivity legalNoticesActivity);

    void inject(SignatureActivity signatureActivity);

    void inject(DrawerFragment drawerFragment);
}
