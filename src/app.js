import React, { Component } from 'react';
import { Container, Header, Left, Body, Right, Button, Icon, Title } from 'native-base';

import {Platform} from 'react-native';
import {Navigation} from 'react-native-navigation';
import {registerScreens, registerScreenVisibilityListener} from './screens';


// screen related book keeping
registerScreens();
registerScreenVisibilityListener();
const tabs = [{
  label: 'Home',
  screen: 'mc.Home',
  icon: require('../img/home.png'),
  title: 'Home',
},
{
  label: 'Activities',
  screen: 'mc.Report',
  icon: require('../img/mark.png'),
  title: 'Activities',
},
{
  label: 'Settings',
  screen: 'mc.Settings',
  icon: require('../img/settings.png'),
  title: 'Settings',
},
{
  label: 'Plugins',
  screen: 'mc.Plugins',
  icon: require('../img/plugin.png'),
  title: 'Plugins',
},
{
  label: 'Supported Markers',
  screen: 'mc.Info',
  icon: require('../img/info.png'),
  title: 'Supported Markers',
}];

// this will start our app
Navigation.startTabBasedApp({
  tabs,
  animationType: Platform.OS === 'ios' ? 'slide-down' : 'fade',
  tabsStyle: {
    tabBarBackgroundColor: '#011D33',
    tabBarButtonColor: '#ffffff',
    tabBarSelectedButtonColor: '#18FFFF',
    tabFontFamily: 'BioRhyme-Bold',
  },
  appStyle: {
    backgroundColor: '#000000',
    tabBarBackgroundColor: '#011D33',
    navBarButtonColor: '#ffffff',
    tabBarButtonColor: '#ffffff',
    navBarTextColor: '#ffffff',
    tabBarSelectedButtonColor: '#18FFFF',
    navigationBarColor: '#011D33',
    navBarBackgroundColor: '#011D33',
    statusBarColor: '#002b4c',
    tabFontFamily: 'BioRhyme-Bold',
  }
});
