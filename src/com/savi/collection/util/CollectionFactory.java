package com.savi.collection.util;

import java.util.HashMap;
import java.util.Map;

import com.savi.base.model.Switchbasicinfo;
import com.savi.collection.dao.SwitchBasicInfoDao;

public class CollectionFactory {
	private static Map<Long,CollectionTask> mapStore = new HashMap<Long,CollectionTask>();
	//输入参数必须为一个从数据库中取出的Switchbasicinfo持久化类对象
	//TODO:新添加一个交换机后调用该方法开始对其进行数据周期采集
	static public void resgisterForPoll(Switchbasicinfo switchBasicInfo){
		CollectionTask collectionTask=new CollectionTask(switchBasicInfo.getId());
		mapStore.put(switchBasicInfo.getId(), collectionTask);
	}
	//输入参数必须为一个从数据库中取出的Switchbasicinfo持久化类对象
	//TODO:删除一个交换机后调用该方法结束对其进行数据周期采集
	static public boolean stopCollection(Switchbasicinfo switchBasicInfo){
		CollectionTask collectionTask=mapStore.get(switchBasicInfo.getId());
		if(collectionTask!=null){
			boolean result=collectionTask.cancel();
			mapStore.remove(switchBasicInfo.getId());
			return result;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		SwitchBasicInfoDao switchBasicInfoDao=new SwitchBasicInfoDao();
		Switchbasicinfo switchBasicInfo=switchBasicInfoDao.getSwitchBasicInfo(new Long(1));
		CollectionFactory.resgisterForPoll(switchBasicInfo);
	}
	
}
