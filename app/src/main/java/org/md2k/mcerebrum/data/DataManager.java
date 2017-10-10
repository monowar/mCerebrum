package org.md2k.mcerebrum.data;
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

import org.md2k.mcerebrum.MyApplication;
import org.md2k.mcerebrum.configuration.AppFile;
import org.md2k.mcerebrum.configuration.ConfigFile;
import org.md2k.mcerebrum.configuration.DataFileManager;
import org.md2k.mcerebrum.configuration.StudyFile;
import org.md2k.system.app.ApplicationManager;
import org.md2k.system.constant.MCEREBRUM;
import org.md2k.system.provider.DataCPManager;

public class DataManager {
    private DataFileManager dataFileManager;
    private DataCPManager dataCPManager;
    public ApplicationManager applicationManager;

    public DataManager(DataFileManager dataFileManager, DataCPManager dataCPManager) {
        this.dataCPManager = dataCPManager;
        this.dataFileManager = dataFileManager;
        if (dataFileManager.getDataFile() == null || dataFileManager.getDataFile().getConfig() == null
                || dataCPManager.isNew(dataFileManager.getDataFile().getConfig().getId(), dataFileManager.getDataFile().getConfig().getType())) {
            loadDefault();
        }
        applicationManager=new ApplicationManager(MyApplication.getContext(), dataCPManager.getAppCPs());

    }

    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }

    public void loadDefault() {
        if(applicationManager!=null) applicationManager.stopMCerebrumService();
        dataCPManager.deleteAll();
        dataFileManager.loadFromAsset();
        prepareDP();
        dataCPManager.getUserCP().set(MyApplication.getContext(), MCEREBRUM.CONFIG.TYPE_FREEBIE, "Freebie");
        applicationManager=new ApplicationManager(MyApplication.getContext(), dataCPManager.getAppCPs());

    }

    public void resetDataCP(String type, String userName) {
        applicationManager.stopMCerebrumService();
        dataCPManager.deleteAll();
        prepareDP();
        dataCPManager.getUserCP().set(MyApplication.getContext(), type, userName);
        applicationManager=new ApplicationManager(MyApplication.getContext(), dataCPManager.getAppCPs());
    }

    private void prepareDP() {
        ConfigFile configFile = dataFileManager.getDataFile().getConfig();
        dataCPManager.setConfigCP(configFile.getId(), configFile.getType(), configFile.getTitle(), configFile.getSummary(), configFile.getDescription(), configFile.getVersion(), configFile.getUpdate(), configFile.getExpected_version(), configFile.getDownload_link(), configFile.getFile_name());

        StudyFile studyFile = dataFileManager.getDataFile().getStudy();
        dataCPManager.setStudyCP(studyFile.getId(), studyFile.getType(), studyFile.getTitle(), studyFile.getSummary(), studyFile.getDescription(), studyFile.getVersion(), studyFile.getIcon(), studyFile.getCover_image(), studyFile.getStart_at_boot());

        AppFile[] appFiles = dataFileManager.getDataFile().getApps();
        for (AppFile appFile : appFiles) {
            if (appFile.getUse_as().equalsIgnoreCase(MCEREBRUM.APP.USE_AS_NOT_IN_USE)) continue;
            dataCPManager.setAppCPs(appFile.getId(), appFile.getType(), appFile.getTitle(), appFile.getSummary(), appFile.getDescription(), appFile.getPackage_name(), appFile.getDownload_link(), appFile.getUpdate(), appFile.getUse_as(), appFile.getExpected_version(), appFile.getIcon());
        }
        applicationManager=new ApplicationManager(MyApplication.getContext(), dataCPManager.getAppCPs());

    }

    public void updateDataDP() {
        dataCPManager.deleteForUpdate();
        prepareDP();
    }

    public DataFileManager getDataFileManager() {
        return dataFileManager;
    }

    public DataCPManager getDataCPManager() {
        return dataCPManager;
    }

    public boolean isStartAtBoot() {
        if (dataCPManager.getStudyCP().getStartAtBoot() && dataCPManager.getStudyCP().getStarted())
            return true;
        return false;
    }
}