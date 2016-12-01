package com.cxypub.baseframework.sdk.solrj;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolrClientConnecter {

	Logger log = Logger.getLogger(this.getClass());

	@Value("${solr_zkHost}")
	private String zkHost = "192.168.1.215:2181,192.168.1.215:2182,192.168.1.215:2183";

	@Value("${defaultCollection}")
	private String defaultCollection = "oaodz";

	private int zkClientTimeout = 20000;

	private int zkConnectTimeout = 1000;

	private CloudSolrClient client = null;

	public synchronized CloudSolrClient getClient() {
		if (client == null) {
			client = new CloudSolrClient(zkHost);
			client.setDefaultCollection(defaultCollection);
			client.setZkClientTimeout(zkClientTimeout);
			client.setZkConnectTimeout(zkConnectTimeout);
			client.connect();
			log.debug("solr客户端连接成功");
		}
		return client;
	}

	public void search(String keyWord) {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyWord);
		try {
			QueryResponse response = this.getClient().query(query);
			SolrDocumentList docs = response.getResults();
			System.out.println("文档个数：" + docs.getNumFound());
			System.out.println("查询时间：" + response.getQTime());
			for (SolrDocument doc : docs) {
				/**       * 每个doc的结构<str name="id">solrtest1_3</str>      * <str name="detail">李四睡觉</str>      * <str name="tabletag">solrtest1</str>      * <str name="position">25.09,116.2</str>       * <long name="_version_">1528509412933632000</long>      */
				String id = (String) doc.getFieldValue("id");
				String detail = (String) doc.getFieldValue("detail");
				String tabletag = (String) doc.getFieldValue("tabletag");
				String position = (String) doc.getFieldValue("position");
				System.out.println("id=" + id + ", detail=" + detail + ", tabletag=" + tabletag + ", position=" + position);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknowned Exception!!!!");
			e.printStackTrace();
		}
	}

	public String getDefaultCollection() {
		return defaultCollection;
	}

	public void setDefaultCollection(String defaultCollection) {
		this.defaultCollection = defaultCollection;
		client.setDefaultCollection(defaultCollection);

	}

	public int getZkClientTimeout() {
		return zkClientTimeout;
	}

	public void setZkClientTimeout(int zkClientTimeout) {
		this.zkClientTimeout = zkClientTimeout;
		client.setZkClientTimeout(zkClientTimeout);
	}

	public int getZkConnectTimeout() {
		return zkConnectTimeout;
	}

	public void setZkConnectTimeout(int zkConnectTimeout) {
		this.zkConnectTimeout = zkConnectTimeout;
		client.setZkConnectTimeout(zkConnectTimeout);
	}

	public static void main(String[] args) {
		System.out.println("测试结束");
	}
}