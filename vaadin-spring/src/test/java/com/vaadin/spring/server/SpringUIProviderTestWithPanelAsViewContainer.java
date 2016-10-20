/*
 * Copyright 2015-2016 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vaadin.spring.server;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.vaadin.navigator.Navigator.SingleComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

/**
 * Test for normal (full) use cases of SpringUIProvider with automatic
 * navigation configuration on the view with a Panel as the view container.
 */
@ContextConfiguration
@WebAppConfiguration
public class SpringUIProviderTestWithPanelAsViewContainer
        extends AbstractSpringUIProviderTest {

    @SpringUI
    private static class TestUI extends UI {
        @Override
        protected void init(VaadinRequest request) {
        }
    }

    @UIScope
    @Component
    @ViewContainer
    private static class MyPanel extends Panel {
    }

    @Configuration
    @EnableVaadinNavigation
    static class Config extends AbstractSpringUIProviderTest.Config {
        @Bean
        public MyPanel myPanel() {
            return new MyPanel();
        }

        // this gets configured by the UI provider
        @Bean
        public TestUI ui() {
            return new TestUI();
        }
    }

    @Test
    public void testConfigureNavigator() {
        TestUI ui = createUi(TestUI.class);
        Assert.isInstanceOf(SingleComponentContainerViewDisplay.class,
                ui.getNavigator().getDisplay(),
                "Navigator is not configured for SingleComponentContainerViewDisplay");
    }

    @Test
    public void testFindViewContainer() throws Exception {
        TestUI ui = createUi(TestUI.class);
        Assert.isInstanceOf(MyPanel.class,
                getUiProvider().findViewContainer(ui),
                "View container is not a Panel");
    }

    @Test
    public void testFindViewContainerMultipleTimes() throws Exception {
        testFindViewContainer();
        testFindViewContainer();
        testFindViewContainer();
    }

}
