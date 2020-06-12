#!/bin/bash
cmUrl="http://archive.cloudera.com/cm5/cm/5/cloudera-manager-el6-cm5.16.2_x86_64.tar.gz"
parcelUrl="http://archive.cloudera.com/cdh5/parcels/latest/CDH-5.16.2-1.cdh5.16.2.p0.8-el6.parcel"
parcelSha1Url="http://archive.cloudera.com/cdh5/parcels/latest/CDH-5.16.2-1.cdh5.16.2.p0.8-el6.parcel.sha1"
parcelManifestUrl="http://archive.cloudera.com/cdh5/parcels/latest/manifest.json"

sudo mkdir /opt/software
cd /opt/software
wget -b $parcelSha1Url
wget -b $parcelManifestUrl
wget -b $cmUrl
wget -b $parcelUrl