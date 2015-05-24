package com.savi.show.action;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.savi.base.model.Switchcur;
import com.savi.show.dao.IfInterfaceDao;
import com.savi.show.dao.SwitchDao;
import com.savi.show.dto.BindingTableInfo;
import com.savi.show.dto.InterfaceInfo;

@SuppressWarnings("serial")
public class RealTimeAction extends BaseAction {
	private SwitchDao switchDao = new SwitchDao();
	private IfInterfaceDao interfaceDao = new IfInterfaceDao();

	private Long switchbasicinfoId;
	private Integer ipVersion;

	private Integer bindingType;
	private String ifIndex;

	private List<InterfaceInfo> interfaceList;// 交换机端口列表
	private List<BindingTableInfo> bindingTableInfoList;// 绑定表信息列表

	private int totalCount;
	private String start;
	private String limit;

	@JSON(serialize = false)
	public String listSwitchInterface() throws Exception {
		Switchcur switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
				ipVersion, switchbasicinfoId);
		while(switchcur==null){
			Thread.sleep(300);
			switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
					ipVersion, switchbasicinfoId);
		}
		List<Integer> list = new LinkedList<Integer>();
		interfaceList = switchDao.getRealTimeInterfaceList(switchcur.getId(),
				start, limit, list);
		totalCount = list.get(0);
		return SUCCESS;
	}

	@JSON(serialize = false)
	public String listSwitchBindingTableInfo() throws Exception {
		Switchcur switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
				ipVersion, switchbasicinfoId);
		while(switchcur==null){
			Thread.sleep(300);
			switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
					ipVersion, switchbasicinfoId);
		}
		List<Integer> list = new LinkedList<Integer>();
		bindingTableInfoList = switchDao.getRealTimeBindingTableInfoList(
				switchcur.getId(), start, limit, list);
		totalCount = list.get(0);
		return SUCCESS;
	}

	@JSON(serialize = false)
	public String listSwitchBindingTableInfoByType() throws Exception {
		Switchcur switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
				ipVersion, switchbasicinfoId);
		while(switchcur==null){
			Thread.sleep(300);
			switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
					ipVersion, switchbasicinfoId);
		}
		List<Integer> list = new LinkedList<Integer>();
		bindingTableInfoList = switchDao.getRealTimeBindingTableInfoListByType(
				switchcur.getId(), bindingType, start, limit, list);
		totalCount = list.get(0);
		return SUCCESS;
	}

	@JSON(serialize = false)
	public String listInterfaceBindingTableInfo() throws Exception {
		Switchcur switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
				ipVersion, switchbasicinfoId);
		while(switchcur==null){
			Thread.sleep(300);
			switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
					ipVersion, switchbasicinfoId);
		}
		List<Integer> list = new LinkedList<Integer>();
		bindingTableInfoList = interfaceDao.getRealTimeBindingTableInfoList(
				ifIndex, switchcur.getId(), start, limit, list);
		totalCount = list.get(0);
		return SUCCESS;
	}

	@JSON(serialize = false)
	public String listInterfaceBindingTableInfoByType() throws Exception {
		Switchcur switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
				ipVersion, switchbasicinfoId);
		while(switchcur==null){
			Thread.sleep(300);
			switchcur = switchDao.getSwitchcurByIPVersionAndSwitchId(
					ipVersion, switchbasicinfoId);
		}
		List<Integer> list = new LinkedList<Integer>();
		bindingTableInfoList = interfaceDao
				.getRealTimeBindingTableInfoListByType(ifIndex, bindingType,
						switchcur.getId(), start, limit, list);
		totalCount = list.get(0);
		return SUCCESS;
	}

	public String getIfIndex() {
		return ifIndex;
	}

	public void setIfIndex(String ifIndex) {
		this.ifIndex = ifIndex;
	}

	public List<InterfaceInfo> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<InterfaceInfo> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public List<BindingTableInfo> getBindingTableInfoList() {
		return bindingTableInfoList;
	}

	public void setBindingTableInfoList(
			List<BindingTableInfo> bindingTableInfoList) {
		this.bindingTableInfoList = bindingTableInfoList;
	}

	public Integer getBindingType() {
		return bindingType;
	}

	public void setBindingType(Integer bindingType) {
		this.bindingType = bindingType;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Long getSwitchbasicinfoId() {
		return switchbasicinfoId;
	}

	public void setSwitchbasicinfoId(Long switchbasicinfoId) {
		this.switchbasicinfoId = switchbasicinfoId;
	}

	public Integer getIpVersion() {
		return ipVersion;
	}

	public void setIpVersion(Integer ipVersion) {
		this.ipVersion = ipVersion;
	}

}
