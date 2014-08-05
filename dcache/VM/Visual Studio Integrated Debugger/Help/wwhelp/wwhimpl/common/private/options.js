// Copyright (c) 2001-2003 Quadralay Corporation.  All rights reserved.
//

function  WWHCommonSettings_Object()
{
  this.mTitle = "help_ws_vsid";

  this.mbCookies            = true;
  this.mCookiesDaysToExpire = 30;
  this.mCookiesID           = "WDUzPzef_DM";

  this.mAccessible = "false";

  this.mbSyncContentsEnabled  = true;
  this.mbPrevEnabled          = true;
  this.mbNextEnabled          = true;
  this.mbRelatedTopicsEnabled = false;
  this.mbEmailEnabled         = true;
  this.mbPrintEnabled         = true;
  this.mbBookmarkEnabled      = false;
  this.mbPDFEnabled           = false;

  this.mEmailAddress = "docfeedback@vmware.com";

  this.mRelatedTopics = new WWHCommonSettings_RelatedTopics_Object();
  this.mALinks        = new WWHCommonSettings_ALinks_Object();
  this.mPopup         = new WWHCommonSettings_Popup_Object();

  this.mbHighlightingEnabled        = true;
  this.mHighlightingForegroundColor = "#FFFFFF";
  this.mHighlightingBackgroundColor = "#333399";
}

function  WWHCommonSettings_RelatedTopics_Object()
{
  this.mWidth = 250;

  this.mTitleFontStyle       = "font-family: Arial, Helvetica, sans-serif ; font-size: 10pt";
  this.mTitleForegroundColor = "#FFFFFF";
  this.mTitleBackgroundColor = "#999999";

  this.mFontStyle       = "font-family: Arial, Helvetica, sans-serif ; font-size: 8pt";
  this.mForegroundColor = "#003399";
  this.mBackgroundColor = "#FFFFFF";
  this.mBorderColor     = "#666666";

  this.mbInlineEnabled = false;
  this.mInlineFontStyle = "font-family: Arial, Helvetica, sans-serif ; font-size: 10pt";
  this.mInlineForegroundColor = "#003366";
}

function  WWHCommonSettings_ALinks_Object()
{
  this.mbShowBook = false;

  this.mWidth  = 250;
  this.mIndent = 17;

  this.mTitleFontStyle       = "font-family: Arial, Helvetica, sans-serif ; font-size: 10pt";
  this.mTitleForegroundColor = "#FFFFFF";
  this.mTitleBackgroundColor = "#999999";

  this.mFontStyle       = "font-family: Arial, Helvetica, sans-serif ; font-size: 8pt";
  this.mForegroundColor = "#003399";
  this.mBackgroundColor = "#FFFFFF";
  this.mBorderColor     = "#666666";
}

function  WWHCommonSettings_Popup_Object()
{
  this.mWidth = 200;

  this.mBackgroundColor = "#FFFFCC";
  this.mBorderColor     = "#999999";
}
