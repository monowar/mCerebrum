package org.md2k.mcerebrum.menu;
/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import android.content.Context;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.md2k.mcerebrum.data.userinfo.UserInfo;
import org.md2k.mcerebrum.data.userinfo.UserInfoServer;

public class Server extends Menu{
    IProfile[] getHeaderContent(Context context, UserInfo user, final ResponseCallBack responseCallBack){
        IProfile[] iProfiles=new IProfile[4];
        iProfiles[0]=new ProfileDrawerItem().withName(user.getName(context)).withIcon(user.getIcon(context));
        iProfiles[1]=new ProfileSettingDrawerItem().withName("About Study").withIcon(FontAwesome.Icon.faw_info).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                responseCallBack.onResponse(OP_ABOUT_STUDY);
                return false;
            }
        });
        if(((UserInfoServer)user).isLoggedIn(context)){
            iProfiles[2] = new ProfileSettingDrawerItem().withName("Login").withIcon(FontAwesome.Icon.faw_sign_in).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    responseCallBack.onResponse(OP_LOGIN);
                    return false;
                }
            });
        }else{
            iProfiles[2] = new ProfileSettingDrawerItem().withName("Logout").withIcon(FontAwesome.Icon.faw_sign_in).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    responseCallBack.onResponse(OP_LOGOUT);
                    return false;
                }
            });
        }
        iProfiles[3]= new ProfileSettingDrawerItem().withName("Leave Study").withIcon(FontAwesome.Icon.faw_chain_broken).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                responseCallBack.onResponse(OP_LEAVE);
                return false;
            }
        });
        return iProfiles;
    }
    IDrawerItem[] getMenuContent(ResponseCallBack responseCallBack){
        return getMenuContent(menuContent, responseCallBack);
    }
    private static MenuContent[] menuContent = new MenuContent[]{
            new MenuContent("Home", FontAwesome.Icon.faw_home, MenuContent.PRIMARY_DRAWER_ITEM, OP_HOME),
            new MenuContent("Settings", FontAwesome.Icon.faw_cog, MenuContent.PRIMARY_DRAWER_ITEM, OP_SETTINGS),
            new MenuContent("Step by Step Settings", FontAwesome.Icon.faw_cogs, MenuContent.PRIMARY_DRAWER_ITEM, OP_SETTINGS),
            new MenuContent("Start Study", FontAwesome.Icon.faw_play, MenuContent.PRIMARY_DRAWER_ITEM, OP_SETTINGS)
//            new MenuContent("Report",FontAwesome.Icon.faw_bar_chart,MenuContent.PRIMARY_DRAWER_ITEM, OP_REPORT),
//            new MenuContent("Plot",FontAwesome.Icon.faw_line_chart,MenuContent.PRIMARY_DRAWER_ITEM, OP_PLOT),
//            new MenuContent("Export Data",FontAwesome.Icon.faw_upload,MenuContent.PRIMARY_DRAWER_ITEM, OP_EXPORT_DATA),
//            new MenuContent("Help",null,MenuContent.SECTION_DRAWER_ITEM, OP_HELP),
//            new MenuContent("Quick Tour",FontAwesome.Icon.faw_film,MenuContent.SECONDARY_DRAWER_ITEM, OP_QUICK_TOUR),
//            new MenuContent("Frequently asked question",FontAwesome.Icon.faw_question_circle,MenuContent.SECONDARY_DRAWER_ITEM),
//            new MenuContent("About",null,MenuContent.SECTION_DRAWER_ITEM, OP_ABOUT),
//            new MenuContent("Who we are",FontAwesome.Icon.faw_users,MenuContent.SECONDARY_DRAWER_ITEM),
//            new MenuContent("Terms and Conditions",FontAwesome.Icon.faw_pencil_square_o,MenuContent.SECONDARY_DRAWER_ITEM),
//            new MenuContent("Privacy Policy",FontAwesome.Icon.faw_lock,MenuContent.SECONDARY_DRAWER_ITEM),
//            new MenuContent("Contact",FontAwesome.Icon.faw_envelope_o,MenuContent.SECONDARY_DRAWER_ITEM),
//            new MenuContent("Feedback",FontAwesome.Icon.faw_comment,MenuContent.SECONDARY_DRAWER_ITEM),
    };

}
