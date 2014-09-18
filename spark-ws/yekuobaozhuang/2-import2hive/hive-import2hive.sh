#! /usr/bin/env bash
sqoop --options-file hive-import-oracle.sqoop --table BIGDATA_MPVOLUME_ONEROW --split-by TRADE_CODE
