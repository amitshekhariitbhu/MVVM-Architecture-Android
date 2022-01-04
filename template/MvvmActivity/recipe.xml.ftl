<?xml version="1.0"?>
<#import "root://activities/common/kotlin_macros.ftl" as kt>
<recipe>
    <#include "../common/recipe_manifest.xml.ftl" />
    <@kt.addAllKotlinDependencies />

    <dependency mavenUrl="com.android.support:appcompat-v7:${buildApi}.+"/>
    <dependency mavenUrl="com.android.support.constraint:constraint-layout:+" />
    <dependency mavenUrl="android.arch.lifecycle:extensions:+"/>
    <#if generateKotlin && useAndroidX>
        <dependency mavenUrl="androidx.lifecycle:lifecycle-viewmodel-ktx:+"/>
    </#if>

    <instantiate from="root/res/layout/activity.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(activityLayout)}.xml" />

    <instantiate from="root/src/app_package/Activity.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${folderName}/view/${activityClass}.${ktOrJavaExt}" />

    <instantiate from="root/src/app_package/ViewModel.${ktOrJavaExt}.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${folderName}/viewmodel/${className}ViewModel.${ktOrJavaExt}" />

</recipe>
